const path = require('path');
const webpack = require('webpack');

module.exports = {
    devtool: 'inline-source-map',
    devServer: {
        historyApiFallback: true,
        compress: true,
        host: '0.0.0.0',
        port: 3000,
        publicPath: '/js/',
        proxy: {
            '**': 'http://localhost:8082'
        }
    },
    plugins: [
        new webpack.NamedModulesPlugin() //prints more readable module names in the browser console on HMR updates
    ]
}