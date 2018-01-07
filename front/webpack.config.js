const path = require('path');
const webpack = require('webpack');

const webpackMerge = require('webpack-merge');
const devConfig = require('./webpack.dev.config.js');
const target = process.env.npm_lifecycle_event;

const ExtractTextPlugin = require('extract-text-webpack-plugin');

const extractSass = new ExtractTextPlugin({
    filename: '[name].[contenthash].css',
    disable: target !== 'prod'
});

const common = {
    entry: './vegan/src/main/resources/static/js/index.js',

    output: {
        path: path.join(__dirname, './vegan/src/main/resources/static/js/'),
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/,
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            },
            {
                test: /\.css$/,
                use: ExtractTextPlugin.extract({
                    fallback: 'style-loader',
                    use: 'css-loader'
                })
            },
            {
                test: /\.scss$/,
                use: extractSass.extract({
                    use: [{
                        loader: 'css-loader'
                    }, {
                        loader: 'sass-loader'
                    }],
                    fallback: 'style-loader'
                })
            },
            {
                test: /\.(png|jpg)$/,
                loader: 'url-loader?limit=100000'
            },
            {
                test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url-loader?limit=10000&mimetype=application/font-woff'
            },
            {
                test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url-loader?limit=10000&mimetype=application/octet-stream'
            },
            {
                test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'file-loader'
            },
            {
                test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url-loader?limit=10000&mimetype=image/svg+xml'
            }
        ]
    },
    plugins: [
        extractSass
    ]
};

const prodConfig = {
    plugins: [
        new webpack.optimize.UglifyJsPlugin({
            compress: {warnings: false}
        })
    ]
};

var config;
if (target === 'prod') {
    console.log('real build');
    config = webpackMerge(common, prodConfig);
} else {
    console.log('dev build');
    config = webpackMerge(common, devConfig);
}

module.exports = config;