package cc.mrbird.febs.common.utils;

import cc.mrbird.febs.common.domain.Tree;
import cc.mrbird.febs.common.domain.router.RouterMeta;
import cc.mrbird.febs.common.domain.router.VueRouter;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    protected TreeUtil() {

    }

    private final static String TOP_NODE_ID = "0";

    /**
     * 用于构建菜单或部门树
     *
     * @param nodes nodes
     * @param <T>   <T>
     * @return <T> Tree<T>
     */
    public static <T> Tree<T> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(node -> {
            String pid = node.getParentId();
            if (pid == null || TOP_NODE_ID.equals(pid)) {
                topNodes.add(node);
                return;
            }
            for (Tree<T> n : nodes) {
                String id = n.getId();
                if (id != null && id.equals(pid)) {
                    if (n.getChildren() == null)
                        n.initChildren();
                    n.getChildren().add(node);
                    node.setHasParent(true);
                    n.setHasChildren(true);
                    n.setHasParent(true);
                    return;
                }
            }
            if (topNodes.isEmpty())
                topNodes.add(node);
        });


        Tree<T> root = new Tree<>();
        root.setId("0");
        root.setParentId("");
        root.setHasParent(false);
        root.setHasChildren(true);
        root.setChildren(topNodes);
        root.setText("root");
        return root;
    }


    /**
     * 构造前端路由
     *
     * @param routes routes
     * @param <T>    T
     * @return ArrayList<VueRouter<T>>
     */
    public static <T> ArrayList<VueRouter<T>> buildVueRouter(List<VueRouter<T>> routes) {
        if (routes == null) {
            return null;
        }

        List<VueRouter<T>> topRoutess = new ArrayList<>();
        VueRouter<T> router1 = new VueRouter<>();
        router1.setPath("/epview");
        router1.setName("企业信息");
        router1.setComponent("enterprise/Enterprise");
        router1.setIcon("none");
        router1.setMeta(new RouterMeta(true, false));
        topRoutess.add(router1);

        VueRouter<T> router2 = new VueRouter<>();
        router2.setPath("/exview");
        router2.setName("专家信息");
        router2.setComponent("expert/Expert");
        router2.setIcon("none");
        router2.setMeta(new RouterMeta(true, false));
        topRoutess.add(router2);

        List<VueRouter<T>> topRoutes = new ArrayList<>();
        VueRouter<T> router = new VueRouter<>();
        router.setName("企业&专家");
        router.setPath("/home");
        router.setComponent("HomePageView");
        router.setIcon("home");
        router.setChildren(topRoutess);
        router.setMeta(new RouterMeta(false, true));
        topRoutes.add(router);

        routes.forEach(route -> {
            String parentId = route.getParentId();
            if (parentId == null || TOP_NODE_ID.equals(parentId)) {
                topRoutes.add(route);
                return;
            }
            for (VueRouter<T> parent : routes) {
                String id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null)
                        parent.initChildren();
                    parent.getChildren().add(route);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    return;
                }
            }
        });

        ArrayList<VueRouter<T>> list = new ArrayList<>();
        VueRouter<T> root = new VueRouter<>();
        root.setName("主页");
        root.setComponent("MenuView");
        root.setIcon("none");
        root.setPath("/");
        root.setRedirect("/home");
        root.setChildren(topRoutes);
        list.add(root);

        root = new VueRouter<>();
        root.setName("404");
        root.setComponent("error/404");
        root.setPath("*");
        list.add(root);

        return list;
    }
}