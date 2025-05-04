<template>
  <div class="vocab-detail">
    <div class="card" style="width: 18rem;">
      <div class="card-body">
        <div class="card-header  align-items-center">
          <div>
            <h5 class="card-title">{{ props.detailVocabList.length > 0 ? props.detailVocabList[0].word : "" }}</h5>
          </div>
          <div>
            <button type="button" class="btn save-icon" @click="toggleEditStatus()" :disabled="!isEditable">Save</button>
            <button type="button" class="btn edit-icon" @click="toggleEditStatus()">Edit</button>
            <button type="button" class="btn close-icon" @click="$emit('toggleSearchStatus')">&#128473;</button>
          </div>
        </div>
        <div class="card-text pt-3 mx-0 row">
          <ol>
            <li v-for="vocab in props.detailVocabList" class="list-group-item d-flex align-items-start row border-bottom py-2"
            :class="'level-'+vocab.priority">
              <div class="col-11">
                <div class="fw-bold"><input type="text" :value="vocab.meaning" :disabled="!isEditable"></div>
                <div><input type="text" :value="vocab.example" :disabled="!isEditable"></div>
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

import axiosClient from "../config/axiosConfig";
import { ref, onMounted, defineEmits } from 'vue';

import { Vocab } from '../dto/vocab/Vocab';

const props = defineProps<{
  detailVocabList: Vocab[]
}>();

const emit = defineEmits(['toggleSearchStatus']);

const isEditable = ref<boolean>(false);

const toggleEditStatus = () => {
  isEditable.value = !isEditable.value;
  console.log(isEditable.value)
}

</script> 