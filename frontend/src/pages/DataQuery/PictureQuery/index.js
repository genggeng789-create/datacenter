import React, { useRef, useState } from 'react';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import moment from 'moment';
import { Cascader, message } from 'antd';
import { getPicQueryList } from '@/services/dataQuery';
import { markType, markClassArr, markTypeArr } from '@/utils/dataQuery';
import { getUrl } from '@/utils/utils';
import ImgCard from './ImgCard';
import styles from './index.less';

const markStuffClassArr = {
  0: '标注物类别0',
  1: '标注物类别1',
  2: '标注物类别2',
  3: '标注物类别3',
};

const markStuffDescArr = {
  0: '标注物描述0',
  1: '标注物描述1',
  2: '标注物描述2',
  3: '标注物描述3',
};

const PictureQuery = () => {
  const actionRef = useRef();
  const [currentImg, setCurrentImg] = useState('');
  const [imgList, setImgList] = useState([]);
  const [cardVisible, setCardVisible] = useState(false);

  const showImg = (record) => {
    console.log('&&&', record['demo_photo_list']);
    const imgarr = record['demo_photo_list'];
    if (imgarr.length > 0) {
      imgarr.forEach((e, index) => {
        imgarr[index] = `${getUrl()}/api/oss-access/download?fileName=${e}&&path=${
          record.path_md5
        }`;
      });
      setImgList(imgarr);
      setCurrentImg(imgarr[0]);
      setCardVisible(true);
    } else {
      message.info('暂无照片');
    }
  };

  const columns = [
    {
      title: '图片集名称',
      dataIndex: 'package_name',
      align: 'center',
      search: false,
    },
    {
      title: '标注类别',
      dataIndex: 'mark_class',
      valueEnum: markClassArr,
      search: false,
    },
    {
      title: '标注形式',
      dataIndex: 'mark_type',
      align: 'center',
      valueEnum: markTypeArr,
      search: false,
    },
    {
      title: '标注类别',
      key: 'mark',
      hideInTable: true,
      renderFormItem: (e) => <Cascader options={markType} placeholder="请选择" />,
    },
    {
      title: '标注物分类',
      dataIndex: 'mark_stuff_class',
      align: 'center',
    },
    {
      title: '标注物描述',
      dataIndex: 'mark_stuff_desc',
      align: 'center',
    },
    {
      title: '样本数量',
      dataIndex: 'picture_number',
      align: 'center',
    },
    {
      title: '所属项目',
      dataIndex: 'project',
      align: 'center',
    },
    {
      title: '场景描述',
      dataIndex: 'sence',
      align: 'center',
    },
    {
      title: '标注时间',
      valueType: 'dateRange',
      dataIndex: 'formatMarkStartTime',
      align: 'center',
      render: (text, record) => moment(record.formatMarkStartTime).format('YYYY-MM-DD HH:mm'),
    },
    {
      title: '下载链接',
      dataIndex: 'copyLink',
      align: 'center',
      copyable: true,
      search: false,
      ellipsis: true,
      width: 150,
      // renderText: (text, record) => { `wget_pictures.sh ${record.path_md5} ${record.package_name}` }
    },
    {
      title: '操作',
      dataIndex: 'operate',
      align: 'center',
      valueType: 'option',
      render: (text, record) => [
        <a key="1" onClick={() => showImg(record)}>
          {' '}
          查看
        </a>,
      ],
    },
  ];
  return (
    <PageHeaderWrapper>
      <ProTable
        className={styles.picTable}
        actionRef={actionRef}
        columns={columns}
        search={{ span: { xxl: 8 } }}
        pageination={{ defaultPageSize: 10 }}
        rowKey={(record) => record.id}
        beforeSearchSubmit={(params) => {
          console.log('params', params);
          const { mark, formatMarkStartTime, ...rest } = params;
          if (mark && mark.length > 0) {
            rest['mark_class'] = mark[0];
            rest['mark_type'] = mark[1];
          }
          if (formatMarkStartTime && formatMarkStartTime.length > 0) {
            rest['start_markStartTime'] = formatMarkStartTime[0];
            rest['end_markStartTime'] = formatMarkStartTime[1];
          }
          return rest;
        }}
        request={async (params = {}) => {
          const { current, ...rest } = params;
          rest['pageNum'] = current;
          const response = await getPicQueryList(rest);
          const list = response.list;
          if (list.length > 0) {
            list.forEach(
              (e) => (e['copyLink'] = `wget_pictures.sh ${e.path_md5} ${e.package_name}`),
            );
          }
          return {
            data: list,
            page: params.current,
            success: true,
            total: response.total,
          };
        }}
      />
      <ImgCard
        currentImg={currentImg}
        imgList={imgList}
        setCurrentImg={setCurrentImg}
        cardVisible={cardVisible}
        setCardVisible={setCardVisible}
      />
    </PageHeaderWrapper>
  );
};

export default PictureQuery;
