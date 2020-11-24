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
      // target: 'https://dev-platform.dbpay.xin',
      target: 'http://10.16.32.172:8222/',
      changeOrigin: true,
      pathRewrite: {
        '^/api': '',
      },
    },
  },
  test: {
    '/api/': {
      target: 'http://10.16.32.172:8222/',
      changeOrigin: true,
      pathRewrite: {
        '^': '',
      },
    },
  },
  pre: {
    '/api/': {
      target: 'http://10.16.32.172:8222/',
      changeOrigin: true,
      pathRewrite: {
        '^': '',
      },
    },
  },
};
