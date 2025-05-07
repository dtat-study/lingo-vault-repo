<template>
    <div class="login-container">
        <h1 class="logo text-center">ling<span class="logo-underline">o-vault</span></h1>
        <h4 class="text-center">Login to your account</h4>
        <form @submit.prevent="handleLogin">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" class="form-control" v-model="username" placeholder="Enter your username" required />
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" class="form-control"  v-model="password" placeholder="Enter your password" required />
            </div>
            <div v-if="errorMessage" class="error-message">
                {{ errorMessage }}
            </div>
            <button class="mt-3" type="submit">Login</button>
        </form>
        <p class="text-center mt-3">
            Don't have an account?
            <router-link to="/signup">Sign up here</router-link>.
        </p>
    </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import *  as restApi from "../../connect/restApi";


const username = ref("");
const password = ref("");
const errorMessage = ref("");
const router = useRouter();

const handleLogin = async () => {
    // Mock login logic
    let jwrtoken:string = await restApi.login({"username":username.value, "password":password.value});
    localStorage.setItem("jwt-token", jwrtoken);
    if (jwrtoken) {
        // Redirect to the home page after successful login
        router.push("/");
    } else {
        errorMessage.value = "Invalid username or password. Please try again.";
    }
};
</script>
