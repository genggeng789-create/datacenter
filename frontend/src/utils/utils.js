import { parse } from 'querystring';
import * as Keycloak from 'keycloak-js';
import { getDvaApp } from 'umi';
import pathRegexp from 'path-to-regexp';
import moment from 'moment';

// keycloak配置
export function getKeycloakOptions() {
  const hostStr = window.location.host;
  let url = 'https://accounts-internal.deepblueai.com/auth';
  let realm = 'dev';

  if (hostStr) {
    if (hostStr.toLowerCase().startsWith('localhost')) {
      url = 'https://accounts-internal.deepblueai.com/auth';
      realm = 'dev';
    } else if (hostStr.toLowerCase().startsWith('dev-')) {
      url = 'https://accounts-internal.deepblueai.com/auth';
      realm = 'dev';
    } else if (hostStr.toLowerCase().startsWith('test-')) {
      url = 'https://accounts-internal.deepblueai.com/auth';
      realm = 'test';
    } else if (hostStr.toLowerCase().startsWith('uat-')) {
      url = 'https://accounts-internal.deepblueai.com/auth';
      realm = 'uat';
    } else {
      url = 'https://accounts-internal.deepblueai.com/auth';
      realm = 'prod';
    }
  }
  return {
    url: url,
    realm: realm,
    clientId: 'web-data-center',
    onLoad: 'login-required',
    'enable-cors': true,
    'cors-allowed-methods': 'POST,PUT,DELETE,GET,OPTIONS,PATCH',
    'cors-allowed-headers': 'Authorization, Origin, X-Requested-With, Content-Type',
  };
}

// keycloak初始化
export function keycloakInit(routes, callBack) {
  const initOptions = getKeycloakOptions();
  let keycloak = Keycloak(initOptions);
  keycloak
    .init({ onLoad: initOptions.onLoad })
    .success((auth) => {
      keycloak.loadUserInfo().success((userInfo) => {
        localStorage.setItem('current', JSON.stringify(userInfo));
        localStorage.setItem('mobile', userInfo.mobile || userInfo.preferred_username);

        localStorage.setItem('userName', userInfo.name || userInfo.preferred_username);
        localStorage.setItem('userId', userInfo.userId);
        getDvaApp()._store.dispatch({
          type: 'global/keycloak',
          payload: keycloak,
        });
      });
      localStorage.setItem('react-token', keycloak.token);
      localStorage.setItem('react-refresh-token', keycloak.refreshToken);
    })
    .error(() => {
      console.error('Authenticated Failed');
    });
}

// 获取路由参数
export const getPageQuery = () => parse(window.location.href.split('?')[1]);

// 获取对应路由的角色
export const getAuthorityFromRouter = (router = [], pathname) => {
  const authority = router.find(
    ({ routes, path = '/' }) =>
      (path && pathRegexp(path).exec(pathname)) ||
      (routes && getAuthorityFromRouter(routes, pathname)),
  );
  if (authority) return authority;
  return undefined;
};

// 获取对应环境前缀
export function getUrl() {
  const hostStr = window.location.host;
  let uriStr = 'http://test-dam.deepblueai.com/';
  if (hostStr) {
    if (hostStr.toLowerCase().startsWith('localhost')) {
      uriStr = 'http://test-dam.deepblueai.com/';
    } else if (hostStr.toLowerCase().startsWith('dev-')) {
      uriStr = 'http://test-dam.deepblueai.com/';
    } else if (hostStr.toLowerCase().startsWith('test-')) {
      uriStr = 'http://test-dam.deepblueai.com/';
    } else if (hostStr.toLowerCase().startsWith('uat-')) {
      uriStr = 'http://uat-dam.deepblueai.com/';
    } else {
      uriStr = 'https://';
    }
  }
  return uriStr;
}
