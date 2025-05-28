import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import "./assets/main.css";

const app = createApp(App);


app.config.errorHandler = (err, instance, info) => {
    // Handle the error
    console.error("Error occurred:", err);
    console.error("Component instance:", instance);
    console.error("Error info:", info);
    router.push({ name: "error" });
}
app.use(router).mount("#app");
