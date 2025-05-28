<template>
  <div class="vocab-detail">
    <div class="card" style="width: 18rem;">
      <div class="card-body">
        <div class="card-header  align-items-center">
          <div>
            <h5 class="card-title">{{ cloneDetailVocabList.length > 0 ? cloneDetailVocabList[0].word : "" }}</h5>
          </div>
          <div>
            <button type="button" class="btn save-icon" @click="updateVocab()" :disabled="!isEditable">Save</button>
            <button type="button" class="btn edit-icon" @click="toggleEditStatus()">Edit</button>
            <button type="button" class="btn close-icon" @click="$emit('toggleSearchStatus')">&#128473;</button>
          </div>
        </div>
        <div class="card-text pt-3 mx-0 row">
          <ol>
            <li v-for="vocab in cloneDetailVocabList"
              class="list-group-item d-flex align-items-start row border-bottom py-2" :class="'level-' + vocab.level">
              <div class="col-11">
                <div class="fw-bold"><input type="text" v-model="vocab.meaning" :disabled="!isEditable"
                    @keyup="checkDuplicate(vocab)" :class="{ 'duplicated': isDuplicated }"></div>
                <div><input type="text" v-model="vocab.example" :disabled="!isEditable" @keyup="checkDuplicate(vocab)">
                </div>
              </div>
              <div class="col-1 row px-0 mx-0 pt-3">
                <div class="col-6 pe-2 ps-0 text-center">{{ vocab.status }}</div>
                <input class="form-check-input col-6 px-2" type="checkbox" value="" id="flexCheckDefault">
              </div>
            </li>
          </ol>

        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

import axiosClient from "../../config/axiosConfig";
import { ref, defineEmits } from 'vue';

import { Vocab } from '../../dto/vocab/Vocab';
import *  as restApi from "../../connect/restApi";

const props = defineProps<{
  detailVocabList: Vocab[]
}>();

const emit = defineEmits(['toggleSearchStatus']);

const isEditable = ref<boolean>(false);
const isDuplicated = ref<boolean>(false);

const cloneDetailVocabList = ref<Vocab[]>([]);
cloneDetailVocabList.value = props.detailVocabList.map((vocab: Vocab) => {
  return { ...vocab };
});

const toggleEditStatus = () => {
  isEditable.value = !isEditable.value;
  console.log(isEditable.value)
}

const checkDuplicate = (vocab: Vocab) => {
  isDuplicated.value = props.detailVocabList.some((existVocab: Vocab) => {
    return existVocab.word === vocab.word && existVocab.meaning === vocab.meaning && existVocab.vocabId !== vocab.vocabId;
  });
}

const getUpdatedList = (updatedFullList: Vocab[]) => {
  let updatedList: Vocab[] = [];
  for (let i = 0, j = 0; i < props.detailVocabList.length; i++, j++) {
    console.log(updatedFullList[j])
    if (updatedFullList[j].vocabId === props.detailVocabList[i].vocabId &&
      (updatedFullList[j].word !== props.detailVocabList[i].word || updatedFullList[j].meaning !== props.detailVocabList[i].meaning)) {
      updatedList.push(updatedFullList[j]);
    }
  };
  return updatedList;
}

const updateVocab = async () => {
  if (isDuplicated.value) {
    alert("Vocab already exists.");
    return;
  }

  let result = await restApi.updateVocabApi(getUpdatedList(cloneDetailVocabList.value));

  if (result === true) {
    alert("UPDATE new vocab successfully.")
  } else {
    alert("UPDATE new vocab failed.")
  }
}

</script>