import React from 'react'
import { Button, Result } from 'antd'
import NoDataSvg from '../../assets/image/common/no-data.svg'

const NoData = ({ onClick, message = 'No data' }) => {
  return (
    <Result
      className="no-data"
      icon={<img src={NoDataSvg} className="udn" alt="" />}
      title={message}
      extra={<Button type="primary" onClick={onClick}>Create now</Button>}
      status="info"
      style={{ marginTop: '50px' }}
    />
  )
}

export default React.memo(NoData)
