export default [
  {
    path: '/',
    component: '../layouts/SecurityLayout',
    routes: [
      {
        path: '/',
        component: '../layouts/BasicLayout',
        routes: [
          {
            path: '/',
            redirect: '/dataQuery/pictureQuery',
          },
          { // 数据查询
            name: 'dataQuery',
            icon: 'dashboard',
            path: '/dataQuery',
            routes: [
              {
                // 图片查询
                name: 'pictureQuery',
                path: '/dataQuery/pictureQuery',
                component: './DataQuery/PictureQuery',
              },
            ]
          },
          {
            component: './404',
          },
        ],
      },
    ],
  },
];
