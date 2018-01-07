import React from 'react';
import Header from './Header';
import Content from './Content';
import RandomNumber from './RandomNumber';

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            value: 102
        };

        this.updateValue = this.updateValue.bind(this);
    }

    updateValue(randomValue) {
        this.setState({
            value: randomValue
        });
        console.log('updated value : ', this.state.value);
    }

    render() {
        return (
            <div>
                <Header title={ this.props.headerTitle }/>
                <Content title={ this.props.contentTitle }
                         body={ this.props.contentBody }/>
                <RandomNumber number={this.state.value}
                              onUpdate={this.updateValue} />
            </div>
        );
    }
}

App.defaultProps = {
    headerTitle: '채식지향주의 - VeganOriented',
    contentTitle: 'Default contentTitle',
    contentBody: 'Default contentBody'
};

export default App;