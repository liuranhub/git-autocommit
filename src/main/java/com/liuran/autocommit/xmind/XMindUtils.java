package com.liuran.autocommit.xmind;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuran.autocommit.xmind.model.Project;
import com.liuran.autocommit.xmind.model.Relation;
import com.liuran.autocommit.xmind.model.TreeNode;
import com.liuran.autocommit.xmind.model.TreeNodeChildren;
import com.liuran.autocommit.xmind.vos.ProjectVo;
import com.liuran.autocommit.xmind.vos.RelationVo;
import com.liuran.autocommit.xmind.vos.TreeNodeVo;
import org.springframework.util.CollectionUtils;
import sun.security.mscapi.CPublicKey;

import java.io.*;
import java.util.*;

public class XMindUtils {

    public static void main(String[] args) {
        String fileName = "D:\\liuran\\tmp\\中台组件关系图\\content.json";
        ProjectVo project = translate(Objects.requireNonNull(readXMind(fileName)));

        List<ProjectVo> list = new ArrayList<>();
        list.add(project);
        //TODO merge

        ProjectVo result = merge(list);


        ObjectMapper mapper = new ObjectMapper();
        try {
            result.getMergeIndex().clear();
            result.getNodeIndex().clear();
            String str = mapper.writeValueAsString(result);
            System.out.println(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return;
    }

    private static ProjectVo merge(List<ProjectVo> projectList){

        ProjectVo projectVo = new ProjectVo();


        // todo merge

        return projectList.get(0);
    }

    private static Project readXMind(String fileName){
        String json = readFile(fileName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readFile(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line ;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static ProjectVo translate(Project project) {
        ProjectVo result = new ProjectVo();
        // 设置category
        result.setCategory(project.getRootTopic().getTitle());

        List<TreeNode> allRootNode = new ArrayList<>();
        List<TreeNodeVo> allRootNodeVo = new ArrayList<>();

        //把项目中心节点当作根节点
        if (project.getRootTopic().getChildren().getAttached() != null) {


            TreeNodeChildren children = new TreeNodeChildren();
            children.setAttached(project.getRootTopic().getChildren().getAttached());

            TreeNode node = new TreeNode();
            node.setChildren(children);

            node.setId(project.getId());
            node.setTitle(project.getRootTopic().getTitle());

            allRootNode.add(node);
        }

        if (project.getRootTopic().getChildren().getDetached() != null) {
            allRootNode.addAll(project.getRootTopic().getChildren().getDetached());
        }

        for (TreeNode node : allRootNode){
            TreeNodeVo treeNodeVo = toVo(node, null);
            translateTreeNode(node, treeNodeVo);
            allRootNodeVo.add(treeNodeVo);
        }
        result.setNodes(allRootNodeVo);

        for (TreeNodeVo node : result.getNodes()){
            Map<String, TreeNodeVo> index = new HashMap<>();
            Map<String, TreeNodeVo> mergeIndex = new HashMap<>();
            refreshIndexAndSetCategory(node, index, mergeIndex, result.getCategory());

            result.putAllMergeIndex(mergeIndex);
            result.putAllNodeIndex(index);
        }

        List<RelationVo> relationVos = new ArrayList<>();
        for (Relation relation : project.getRelationships()) {
            RelationVo relationVo = new RelationVo();
            relationVo.setPath(relation.getId());
            relationVo.setSource(relation.getEnd1Id());
            relationVo.setTarget(relation.getEnd2Id());

            String name = result.getNodeIndex().get(relation.getEnd1Id()).getName()
                    + " => " + result.getNodeIndex().get(relation.getEnd2Id()).getName();
            relationVo.setName(name);

            relationVos.add(relationVo);
        }
        result.setRelations(relationVos);

        return result;
    }

    private static void translateTreeNode(TreeNode parent, TreeNodeVo targetParent){

        if (parent.getChildren() == null) {
            return;
        }

        List<TreeNode> attached = parent.getChildren().getAttached();
        List<TreeNode> detached = parent.getChildren().getDetached();
        if (!CollectionUtils.isEmpty(attached)){
            translateTreeNode(attached, targetParent);
        }

        if (!CollectionUtils.isEmpty(detached)){
            translateTreeNode(detached, targetParent);
        }
    }

    private static void translateTreeNode( List<TreeNode> nodes, TreeNodeVo parent){
        for (TreeNode node : nodes) {
            TreeNodeVo target = toVo(node, parent);
            // 建立父子节点关系
            parent.addChild(target);
            target.setParent(parent);

            translateTreeNode(node, target);
        }
    }

    private static void refreshIndexAndSetCategory(TreeNodeVo node,
                                                   Map<String, TreeNodeVo> index,
                                                   Map<String, TreeNodeVo> mergeIndex,
                                                   String category){
        node.setCategory(category);
        index.put(node.getId(), node);
        if (mergeIndex.get(node.getMergeUniqueId()) != null) {
            System.out.println(node.getMergeUniqueId());
            System.out.println(node.getId() + " repeat");
            System.out.println(mergeIndex.get(node.getMergeUniqueId()).getId());
        }
        mergeIndex.put(node.getMergeUniqueId(), node);
        for (TreeNodeVo n : node.getNodes()) {
            refreshIndexAndSetCategory(n, index, mergeIndex, category);
        }
    }

    private static TreeNodeVo toVo(TreeNode node, TreeNodeVo parent) {
        return new TreeNodeVo(parent, node.getId(), node.getTitle(), null);
    }
}
