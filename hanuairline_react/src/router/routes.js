import { lazy } from 'react'
import { HOME, ADMIN } from './constants'

import MainEntry from '../views/home/main'
import Ticket from '../views/home/ticket/index'
import TicketCreate from '../views/home/ticket/create'
import TicketDetail from '../views/home/ticket/detail'
import Login from '../views/login'
import NoMatch from '../views/exception/no-match'

const routesMap = [
  {
    path: HOME.LOGIN.path,
    exact: true,
    component: Login,
    meta: {
      requiresAuth: false,
      title: HOME.LOGIN.name,
      isLoginToHome: true
    }
  },
  {
    path: HOME.HOME.path,
    component: MainEntry,
    meta: {
      requiresAuth: true,
    },
    childrenRoutes: [
      {
        path: HOME.TICKET.path,
        component: Ticket,
        exact: true,
        meta: {
          title: HOME.TICKET.name,
          requiresAuth: true,
        }
      },
      {
        path: HOME.TICKET_CREATE.path,
        component: TicketCreate,
        exact: true,
        meta: {
          title: HOME.TICKET_CREATE.name,
          requiresAuth: true,
        }
      },
      {
        path: HOME.TICKET_DETAIL.path,
        component: TicketDetail,
        exact: true,
        meta: {
          title: HOME.TICKET_DETAIL.name,
          requiresAuth: true,
        }
      },
    ]
  },
  {
    path: HOME.NO_MATCH.path,
    component: NoMatch,
    meta: {
      requiresAuth: false,
      title: HOME.NO_MATCH.name
    }
  },
]

export default routesMap
