import React from 'react'
import { ADMIN, HOME } from '../router/constants'
import {
  HomeOutlined,
} from '@ant-design/icons'

// TODO: add menu list here
export const END_USER_MENU = [
  {
    path: HOME.HOME_INDEX.path,
    icon: <HomeOutlined />,
    name: HOME.HOME_INDEX.name
  },
  {
    path: HOME.TICKET.path,
    icon: <HomeOutlined />,
    name: HOME.TICKET.name,
  },
];

export const ADMIN_MENU = []
