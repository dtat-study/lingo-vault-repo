<template>
  <div class="container-fluid row">
    <div class="col-12 add-vocab row justify-content-start">
      <AddVocabComponent :originalWordList="originalWordList" @seeVocabDetail="handleSeeVocabDetail" ></AddVocabComponent>
    </div>
    <div class="col-12 list-vocab">
      <ListVocabComponent  ref="childRef" @seeVocabDetail="handleSeeVocabDetail" :originalWordList="originalWordList" ></ListVocabComponent>
    </div>

  </div>
  <VocabDetailComponent :detailWordList="detailWordList" v-if="isDetail" @toggle-search-status="toggleSearchStatus"></VocabDetailComponent>
</template>

<script lang="ts" setup>
import { ref, onMounted, onBeforeMount } from 'vue';
import { Vocab } from '../dto/vocab/Vocab';
import axiosClient from "../config/axiosConfig";

import AddVocabComponent from './AddVocabComponent.vue';
import ListVocabComponent from './ListVocabComponent.vue';
import VocabDetailComponent from './VocabDetailComponent.vue';

const originalWordList =  ref<Vocab[]>([]);
const detailWordList =  ref<Vocab[]>([]);
const socket = new WebSocket("ws://localhost:443/ws/update-word-lists");
const isDetail = ref<boolean>(false);
const childRef = ref<InstanceType<typeof ListVocabComponent> | null>(null)

onMounted(async () => {
    await axiosClient
        .post('/searchByWord',{word: ""})
        .then(response => (originalWordList.value = response.data));
    
        
})

socket.onmessage =async function() {
  await axiosClient
        .post('/searchByWord',{word: ""})
        .then(response => (originalWordList.value = response.data));
        childRef.value?.updateResultList();
};

const toggleSearchStatus = () => {
  isDetail.value = !isDetail.value;
}

const handleSeeVocabDetail = (word: string) => {
  toggleSearchStatus()
  detailWordList.value = originalWordList.value.filter((vocab: Vocab) => {
    return vocab.word === word;
  });
};

</script>
