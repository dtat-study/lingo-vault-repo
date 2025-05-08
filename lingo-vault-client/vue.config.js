const { defineConfig } = require("@vue/cli-service");
const fs = require("fs");
const path = require("path");

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  configureWebpack: {
    devServer: {
      https: {
        key: fs.readFileSync(path.resolve(__dirname, "../localhost-key.pem")),
        cert: fs.readFileSync(path.resolve(__dirname, "../localhost-cert.pem")),
      },
    },
    devtool: "hidden-source-map",
    // Cấu hình sẽ được merge với cấu hình mặc định của Vue CLI
    plugins: [
      // Thêm plugins ở đây
      new (require("webpack").DefinePlugin)({
        "process.env.LINGO_VAULT_API": JSON.stringify(
          process.env.LINGO_VAULT_API
        ),
      }),
    ],
    module: {
      rules: [
        // Thêm rules ở đây
      ],
    },
    resolve: {
      alias: {
        // Thêm alias ở đây
        "@components": "@/components",
      },
      extensions: [".js", ".vue", ".json"], // Thêm extensions nếu cần
    },
    optimization: {
      splitChunks: {
        // Tùy chỉnh split chunks
        chunks: "all",
      },
    },
  },
});
