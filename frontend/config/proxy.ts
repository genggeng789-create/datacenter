/**
 * 在生产环境 代理是无法生效的，所以这里没有生产环境的配置
 * The agent cannot take effect in the production environment
 * so there is no configuration of the production environment
 * For details, please see
 * https://pro.ant.design/docs/deploy
 */
export default {
  mock: {
    '/api': {
      target: 'http://localhost:3333',
      changeOrigin: true,
      pathRewrite: {
        '^': '',
      },
    },
  },
  dev: {
    '/api': {
      target: 'http://test-dam.deepblueai.com/',
      changeOrigin: true,
      pathRewrite: {
        '^/api': '',
      },
    },
  },
  test: {
    '/api/': {
      target: 'http://test-dam.deepblueai.com/',
      changeOrigin: true,
      pathRewrite: {
        '^': '',
      },
    },
  },
  uat: {
    '/api/': {
      target: 'http://uat-dam.deepblueai.com/',
      changeOrigin: true,
      pathRewrite: {
        '^': '',
      },
    },
  },
  pre: {
    '/api/': {
      target: 'http://dam.deepblueai.com/',
      changeOrigin: true,
      pathRewrite: {
        '^': '',
      },
    },
  },
};
