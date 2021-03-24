import React from 'react'
import { Switch, Route, BrowserRouter as Router } from 'react-router-dom'
import {ADMIN, HOME, TICKET, PROMOTION, CONTACT, SIGNIN, SIGNUP} from './CONSTANTS'
import { HomePage, TicketPage, PromotionPage, ContactPage, RegisterPage, LoginPage } from '../end-user/pages'
import {AdminApp} from '../admin/AdminApp'
import {EndUserApp} from '../end-user/EndUserApp'

const routes = [
    {
      path: {ADMIN},
      component: AdminApp
    },
    {
      path: {HOME},
      component: EndUserApp,
      routes: [
        {
          path: {TICKET},
          component: TicketPage
        },
        {
          path: {PROMOTION},
          component: PromotionPage
        },
        {
          path: {CONTACT},
          component: ContactPage
        },
        {
          path: {SIGNIN},
          component: LoginPage
        },
        {
          path: {SIGNUP},
          component: RegisterPage
        }
      ]
    }
];

export const RouterConfig = () => {
    return (
        <Router>
            <Switch>
              {routes.map((route, i) => (
                <RouteWithSubRoutes key={i} {...route} />
              ))}
            </Switch>
        </Router>
      );
}

// A special wrapper for <Route> that knows how to
// handle "sub"-routes by passing them in a `routes`
// prop to the component it renders.
function RouteWithSubRoutes(route) {
    return (
      <Route
        path={route.path}
        render={props => (
          // pass the sub-routes down to keep nesting
          <route.component {...props} routes={route.routes} />
        )}
      />
    );
  }
