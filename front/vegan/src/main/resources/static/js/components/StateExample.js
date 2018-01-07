/**
 * Created by mac on 2018. 1. 1..
 */

import React from 'react';

class StateExample extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            header: 'Header Initial state',
            content: 'Content Initial state'
        };
    }

    updateHeader(text) {
        this.setState({
            header: 'Header has been changed'
        });
    }

    render() {
        return (
            <div>
                <h1>{this.state.header}</h1>
                <h1>{this.state.content}</h1>
                <button onClick={this.updateHeader.bind(this)}>Update</button>
            </div>
        );
    }
}

export default StateExample;