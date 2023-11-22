const path = require('path');
// const HtmlWebpackPlugin = require('html-webpack-plugin/index');


// oggetto configuraione comune
var basicConfig = {
    // Aggiungere configurazione comune a più bundles
    mode: 'development',
    module: {
        rules: [
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
            },
        ],

    },
};

// oggetto configurazione dell'applicazione
var appConfig = Object.assign({}, basicConfig, {
    name: "index",
    entry: {
        index: './src/js/index',
        prova: './src/js/prova',
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, '../main/webapp/resources/js'),
        clean: true,
    },
});

// oggetto di configurazione  - non usato -
var provaConfig = Object.assign({}, basicConfig, {
    plugins: [
        // new HtmlWebpackPlugin({
        //     title: 'Prova Management',
        //     // template: './src/templates/prova.html',
        //     filename: 'WEB-INF/views/prova.html',
        //     // path: path.resolve(__dirname, '../main/webapp/WEB-INF/views'),
        //     // clean: true,
        // }),
    ],
    name: "prova",
    entry: {
        prova: "./src/js/prova",
    },
    output: {
        filename: 'resources/js/[name].bundle.js',
        path: path.resolve(__dirname, '../main/webapp'),
        // clean: true,
    },
});

// Return Array delle configurazioni
module.exports = [
    appConfig,
];


// ---- SINGLE FUNCTION CONFIGURAZIONE ------
// module.exports = {
//     mode: 'development',
//     entry: {
//         index: './src/js/index.js',
//         prova: './src/js/prova.js',
//     },
//     plugins: [
//         // new HtmlWebpackPlugin(),
//         new HtmlWebpackPlugin({
//             title: 'Prova Management',
//             filename: 'prova.html',
//         }),
//     ],
//     output: {
//         filename: '[name].bundle.js',
//         path: path.resolve(__dirname, '../main/webapp/resources/js'),
//         // clean: true,
//     },
// };

