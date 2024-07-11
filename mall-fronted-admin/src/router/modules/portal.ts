export default {
  path: "/portal",
  redirect: "/portal/index",
  meta: {
    icon: "radix-icons:archive",
    // showLink: false,
    title: "门户服务",
    rank: 50
  },
  children: [
    {
      path: "/portal/newproduct",
      name: "portal",
      component: () => import("@/views/portal/newproduct.vue"),
      meta: {
        title: "新品管理",
        showParent: true
      }
    },
    // {
    //   path: "/portal/brand",
    //   name: "brand",
    //   component: () => import("@/views/portal/brand.vue"),
    //   meta: {
    //     title: "品牌管理",
    //     showParent: true
    //   }
    // }
    {
      path: "/portal/brand",
      name: "品牌管理",
      component: () => import("@/views/portal/brand.vue"),
      meta: {
        title: "品牌管理",
        showParent: true
      }
    },
    // {
    //   path: "/portal/recommend",
    //   name: "推荐商品管理",
    //   component: () => import("@/views/portal/recommend.vue"),
    //   meta: {
    //     title: "推荐商品管理",
    //     showParent: true
    //   }
    // }
    // {
    //   path: "/portal/recommend",
    //   name: "推荐商品管理",
    //   component: () => import("@/views/portal/recommend.vue"),
    //   meta: {
    //     title: "推荐商品管理",
    //     showParent: true
    //   }
    // },
    {
      path: "/portal/subject",
      name: "推荐专题管理",
      component: () => import("@/views/portal/subject.vue"),
      meta: {
        title: "推荐专题管理",
        showParent: true
      }
    }
  ]
} satisfies RouteConfigsTable;
