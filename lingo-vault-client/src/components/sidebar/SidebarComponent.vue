<template>
        <div class="sidebar-header text-center">
            <h1 class="logo logo-inline ">lingo</h1>
            <div class="user-name ">Hello User Name</div>
        </div>
        <div class="sidebar-menu text-center">
        <div class="menu">
            <div class="title">Language</div>
            <button class="item" v-for="language in languageList">{{ language }}</button>
        </div>
        </div>
        <div class="sidebar-footer text-center">
            <button class="logout" ><LogOut /> Logout</button>
        </div>
</template>
<script lang="ts" setup>

import { Vocab } from '../../dto/vocab/Vocab';
import { ref } from 'vue';
import { LogOut } from 'lucide-vue-next'

const languageList = ref(["English","Korean"]); 

</script>