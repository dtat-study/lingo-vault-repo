<template>
    <div id="outside-container" class="row">
      <div id="sidebar-component" class="col-1">
        <SidebarComponent />
      </div>
      <div id="english-component" class="col-11">
        <EnglishVocabComponent/>
      </div>
    </div>
  </template>
  <script lang="ts" setup>
  import SidebarComponent from './components/sidebar/SidebarComponent.vue';
  import EnglishVocabComponent from './components/EnglishVocabComponent.vue';
  
  </script>