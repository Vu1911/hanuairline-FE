
//TODO: define all the necessary route constant
export const ADMIN = {
    DASHBOARD: { name: 'Dashboard', path: '/admin/dashboard' },
  }

export const HOME = {
    LOGIN: { name: 'Login', path: ['/', '/login'] },

    HOME: { name: '', path: '/home' },

    HOME_INDEX: { name: 'Home', path: '/home/index' },

    TICKET: { name: 'Ticket', path: '/home/ticket' }, 

    TICKET_CREATE: { name: 'Create Ticket', path: [
        '/home/ticket/create', '/home/ticket/update/:id'
    ] },

    TICKET_DETAIL: { name: '*', path: '/home/ticket/detail/:id' },

    NO_MATCH: { name: '404 Not Found', path: '*' },
}
  