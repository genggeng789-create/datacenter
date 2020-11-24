// 接入模式
export const markType = [

  {
    value: '检测',
    label: '检测',
    children: [
      {
        value: '框',
        label: '框',
      },
      {
        value: '点',
        label: '点',
      },
      {
        value: '线',
        label: '线',
      },
      {
        value: '语义分割',
        label: '语义分割',
      },
      {
        value: '雷达点云',
        label: '雷达点云',
      },
      {
        value: '其他',
        label: '其他',
      }
    ],
  },
  {
    value: "分类",
    label: "分类",
    children: [
      {
        value: '分类',
        label: '分类',
      },
      {
        value: '清洗',
        label: '清洗',
      },
      {
        value: '其他',
        label: '其他',
      },
    ]
  },
  // {
  //   value: "分割",
  //   label: "分割",
  //   children: [
  //     {
  //       value: '分割',
  //       label: '分割',
  //     },
  //     {
  //       value: '清洗',
  //       label: '清洗',
  //     },
  //     {
  //       value: '其他',
  //       label: '其他',
  //     },
  //   ]
  // },
  {
    value: "NLP",
    label: "NLP",
    children: [
      {
        value: '清洗',
        label: '清洗',
      },
      {
        value: '转写',
        label: '转写',
      },
      {
        value: '文本关系',
        label: '文本关系',
      },
      {
        value: '其他',
        label: '其他',
      },
    ]
  },
  {
    value: "ocr识别",
    label: "ocr识别",
    children: [
      {
        value: '中文',
        label: '中文',
      },
      {
        value: '数字',
        label: '数字',
      },
      {
        value: '其他',
        label: '其他',
      },
    ]
  },
]


// 标注类别
export const markClassArr = {
  0: "检测",
  1: "分类",
  2: "分割",
  3: "NLP",
  4: "ocr识别"
}

// 标注形式
export const markTypeArr = {
  "0-0": "框",
  "0-1": "点",
  "0-2": "线",
  "0-3": "雷达点云",
  "0-4": "人脸骨骼打点",
  "0-5": "其他",
  "1-0": "分类",
  "1-1": "其他",
  "2-0": "分割",
  "2-1": "清洗",
  "2-2": "其他",
  "3-0": "NLP",
  "3-1": "其他",
  "4-0": "文字",
  "4-1": "其他",
}