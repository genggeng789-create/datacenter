import React from 'react';
import { PageLoading } from '@ant-design/pro-layout';
import { Redirect, connect } from 'umi';
import { stringify } from 'querystring';
import { keycloakInit } from '@/utils/utils';


class SecurityLayout extends React.Component {

  componentDidMount () {
    const { keycloak, route: { routes }, } = this.props
    if (!keycloak.logout) {
      keycloakInit(routes)
    }
  }

  render () {
    const { children, keycloak, platformId } = this.props;
    // if (!(keycloak.logout && platformId)) {
    //   return <PageLoading />;
    // }
    return children;
  }
}

export default connect(({ global }) => ({
  keycloak: global.keycloak,
  platformId: global.platformId
}))(SecurityLayout);
