import React from 'react'
import './style.scss'
import { Result, Button } from 'antd'
import { withRouter } from 'react-router-dom'

const statusMap = {
  403: {
    title: '403',
    subTitle: 'Sorry, you are not authorized to access this page.',
  },
  404: {
    title: '404',
    subTitle: 'Sorry, the page you visited does not exist.',
  },
  500: {
    title: '500',
    subTitle: 'Sorry, the server is wrong.',
  }
}

const NoMatch = function ({ history, status = '404' }) {
  return (
    <Result
      status={status}
      extra={<Button type="primary" onClick={history.goBack}>Back</Button>}
      {...statusMap[status]}
    />
  )
}

export default withRouter(NoMatch)