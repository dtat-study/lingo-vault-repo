<template>
    <div id="outside-container" class="row">
      <div id="sidebar-component" class=" row justify-content-between flex-column">
        <SidebarComponent />
      </div>
      <div id="language-component">
        <VocabComponent/>
      </div>
    </div>
  </template>
  <script lang="ts" setup>
  import SidebarComponent from '../sidebar/SidebarComponent.vue';
  import VocabComponent from '../vocab/VocabComponent.vue';
  
  </script>