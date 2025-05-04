<template>
  <div class="vocab-detail">
    <div class="card" style="width: 18rem;">
      <div class="card-body">
        <div class="card-header">
          <div>
            <h5 class="card-title">{{ props.detailWordList.length > 0 ? props.detailWordList[0].word : "" }}</h5>
          </div>
          <div>
            <button type="button" class="btn" @click="$emit('toggleSearchStatus')">&#10006;</button>
          </div>
        </div>
        <div class="card-text pt-3 mx-0 row">
          <ol>
            <li v-for="vocab in props.detailWordList" class="list-group-item d-flex align-items-start row border-bottom py-2"
              :class="vocab.priority">
              <div class="col-11">
                <div class="fw-bold">{{ vocab.meaning }}</div>
                <div>{{ vocab.example }}</div>
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
import { Vocab } from '../dto/vocab/Vocab';
import axiosClient from "../config/axiosConfig";
import { ref, onMounted, defineEmits } from 'vue';

const props = defineProps<{
  detailWordList: Vocab[]
}>();
const emit = defineEmits(['toggleSearchStatus']);
</script>