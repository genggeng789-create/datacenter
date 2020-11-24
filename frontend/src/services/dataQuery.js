import request from '@/utils/request';
import { stringify } from 'qs'

//图片查询
export async function getPicQueryList (searchValues = {}) {
  // const params = {
  //   method: 'POST',
  //   body: options,
  // };
  // return request(`/api/search-picture/searchRes`, params);

  return request(`/api/search-picture/searchRes?${stringify(searchValues)}`);
}

