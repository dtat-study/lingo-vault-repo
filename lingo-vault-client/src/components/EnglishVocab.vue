<template>
  <div class="container-fluid row">
    <div class="col-12 add-vocab row justify-content-start">
      <AddVocabComponent :originalVocabList="originalVocabList" @seeVocabDetail="handleSeeVocabDetail" ></AddVocabComponent>
    </div>
    <div class="col-12 list-vocab">
      <ListVocabComponent  ref="childRef" @seeVocabDetail="handleSeeVocabDetail" :originalVocabList="originalVocabList" ></ListVocabComponent>
    </div>

  </div>
  <VocabDetailComponent :detailVocabList="detailVocabList" v-if="isDetail" @toggle-search-status="toggleSearchStatus"></VocabDetailComponent>
</template>

<script lang="ts" setup>

import { ref, onMounted, onBeforeMount } from 'vue';
import axiosClient from "../config/axiosConfig";

import { Vocab } from '../dto/vocab/Vocab';
import AddVocabComponent from './AddVocabComponent.vue';
import ListVocabComponent from './ListVocabComponent.vue';
import VocabDetailComponent from './VocabDetailComponent.vue';
import *  as restApi from "../connect/restApi";


const originalVocabList =  ref<Vocab[]>([]);
const detailVocabList =  ref<Vocab[]>([]);
const socket = new WebSocket("ws://localhost:443/ws/update-word-lists");
const isDetail = ref<boolean>(false);
const childRef = ref<InstanceType<typeof ListVocabComponent> | null>(null)

onMounted(async () => {
  originalVocabList.value = await restApi.searchVocabApi("");
})

socket.onmessage =async function() {
  originalVocabList.value = await restApi.searchVocabApi("");
  childRef.value?.updateResultList();
};

const toggleSearchStatus = () => {
  isDetail.value = !isDetail.value;
}

const handleSeeVocabDetail = (word: string) => {
  toggleSearchStatus()
  detailVocabList.value = originalVocabList.value.filter((vocab: Vocab) => {
    return vocab.word === word;
  });
};

</script>
