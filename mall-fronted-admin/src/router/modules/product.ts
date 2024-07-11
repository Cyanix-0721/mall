export default {
  path: "/product",
  redirect: "/product/product",
  meta: {
    icon: "icon-park-outline:ad-product",
    // showLink: false,
    title: "商品模块",
    rank: 9
  },
  children: [
    {
      path: "/product/product",
      name: "Product",
      component: () => import("@/views/product/product.vue"),
      meta: {
        title: "商品管理",
        showParent: true
      }
    },
    {
      path: "/product/brand",
      name: "Brand",
      component: () => import("@/views/product/brand.vue"),
      meta: {
        title: "商品品牌管理",
        showParent: true
      }
    },
    {
      path: "/product/productCategory",
      name: "ProductCategory",
      component: () => import("@/views/product/productCategory.vue"),
      meta: {
        title: "商品分类管理",
        showParent: true
      }
    }
  ]
} satisfies RouteConfigsTable;
