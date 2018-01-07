/**
 * Created by mac on 2018. 1. 1..
 */

import React from 'react';
import {Helmet} from "react-helmet";
import 'bootstrap/dist/css/bootstrap.css';

export default class Header extends React.Component {
    render() {
        return (
            <div>
                <Helmet>
                    <meta charSet="utf-8" />
                    <title>{this.props.title}</title>
                </Helmet>
                <header>


                </header>
            </div>
        );
    }
}