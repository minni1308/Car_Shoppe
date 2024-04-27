import React from 'react';
import ReactDOM from 'react-dom';
import { Provider,  } from 'react-redux';
import configureStore from './store/configureStore.js'
import HomePage from './components/HomePage.js';
import AppRouter from './routers/AppRouter.jsx';

const store = configureStore();
const jsx = (
    <Provider store={store}>
        <AppRouter />
    </Provider>
);

ReactDOM.render(jsx,document.getElementById('app'));

