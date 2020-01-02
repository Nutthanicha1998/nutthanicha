import Vue from 'vue'
import Router from 'vue-router';
import Reservation from '../components/Reservation';

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: Reservation
        }
    ]
});