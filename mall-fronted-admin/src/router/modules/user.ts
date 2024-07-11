export default {
  path: "/user",
  redirect: "/member/index",
  meta: {
    icon: "radix-icons:face",
    // showLink: false,
    title: "用户服务",
    rank: 50
  },
  children: [
    {
      path: "/member/index",
      name: "Member",
      component: () => import("@/views/user/member.vue"),
      meta: {
        title: "会员列表",
        showParent: true
      }
    },
    {
      path: "/admin/index",
      name: "Admin",
      component: () => import("@/views/user/admin.vue"),
      meta: {
        title: "管理员列表",
        showParent: true
      }
    }
  ]
} satisfies RouteConfigsTable;
