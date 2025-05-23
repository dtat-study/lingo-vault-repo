const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  
  configureWebpack: {
     devtool: 'hidden-source-map',
    // Cấu hình sẽ được merge với cấu hình mặc định của Vue CLI
    plugins: [
      // Thêm plugins ở đây
      new (require('webpack')).DefinePlugin({
        'process.env.LINGO_VAULT_API': JSON.stringify(process.env.LINGO_VAULT_API)
      })
    ],
    module: {
      rules: [
        // Thêm rules ở đây
      ]
    },
    resolve: {
      alias: {
        // Thêm alias ở đây
        '@components': '@/components'
      },
      extensions: ['.js', '.vue', '.json'] // Thêm extensions nếu cần
    },
    optimization: {
      splitChunks: {
        // Tùy chỉnh split chunks
        chunks: 'all'
      }
    }
  }
})