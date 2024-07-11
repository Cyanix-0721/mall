export default {
  path: "/marketing",
  redirect: "/marketing/coupons",
  meta: {
    icon: "ri:android-line",
    // showLink: false,
    title: "营销模块",
    rank: 9
  },
  children: [
    {
      path: "/marketing/coupons",
      name: "Marketing",
      component: () => import("@/views//marketing/coupons.vue"),
      meta: {
        title: "优惠劵管理",
        showParent: true
      }
    },
    {
      path: "/marketing/flashPromotion",
      name: "FlashPromotion",
      component: () => import("@/views//marketing/flashPromotion.vue"),
      meta: {
        title: "秒杀活动管理",
        showParent: true
      }
    },
    {
      path: "/marketing/homeAdvertise",
      name: "HomeAdvertise",
      component: () => import("@/views//marketing/homeAdvertise.vue"),
      meta: {
        title: "首页广告管理",
        showParent: true
      }
    },
    {
      path: "/marketing/flashPromotionProductRelation",
      name: "FlashPromotionProductRelation",
      component: () =>
        import("@/views//marketing/flashPromotionProductRelation.vue"),
      meta: {
        title: "秒杀活动与商品关系管理",
        showParent: true
      }
    }
  ]
} satisfies RouteConfigsTable;
