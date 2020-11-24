import React from 'react'
import { Modal, Tabs, Row, Col } from 'antd'
import styles from './index.less'

const { TabPane } = Tabs;

export default function ImgCard (props) {
  const { currentImg, imgList, setCurrentImg, cardVisible, setCardVisible } = props

  return (
    <div>
      {/* 卡片列表modal */}
      <Modal
        title="图片列表"
        visible={cardVisible}
        onOk={() => { setCardVisible(true); setCurrentImg('') }}
        onCancel={() => { setCardVisible(false); setCurrentImg('') }}
        okText="确定"
        width="70%"
      >
        <Row className={styles.compared}>
          <img src={currentImg} />
        </Row>
        <Row className={styles.comparedBottom}>
          <Col >
            <Tabs activeKey={currentImg} tabPosition="bottom" onChange={(e) => setCurrentImg(e)} className={styles.bottomImgList}>
              {imgList.map((i) => (
                <TabPane tab={<img src={i} />} key={i} onClick={() => setCurrentImg(i)} className={styles.compareImg} />
              ))}
            </Tabs>
          </Col>
        </Row>
      </Modal>
    </div>
  )
}
