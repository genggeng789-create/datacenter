import request from '@/utils/request';
import { stringify } from 'qs';

//图片查询
export async function getPicQueryList(searchValues = {}) {
  return request(`/api/search-picture/searchRes?${stringify(searchValues)}`);
}
