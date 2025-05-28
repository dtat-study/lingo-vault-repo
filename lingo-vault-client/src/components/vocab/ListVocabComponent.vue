<template>
  <div class="vocab-show">
    <div class="vocab-show-option">
      <div class="text-center mb-3 ">Choose the word you want to show!</div>
      <div class="row justify-content-between">
        <div class="col-6">
          <select class="form-select" @change="sortSearchResults()" v-model="sortRule">
            <option value="word">Alphabet</option>
            <option value="createdAt">Added Order</option>
            <option value="level">Learning Progress</option>
          </select>
        </div>
        <div class="col-6">
          <div class="input-group mb-3 row">
            <input type="text" class="form-control col-10" placeholder="Search..." v-model="searchWord" @keyup="filterVocab()">
            <button class="btn btn-outline-secondary col-2" type="button" @click="filterVocab()">Search</button>
          </div>
        </div>
      </div>

    </div>
    <div class="vocab-show-result">
      <ol>
        <li v-for="vocab in searchResults" class="list-group-item d-flex align-items-start row" :class="'level-'+vocab.level"
          @click="seeVocabDetail(vocab.word)">
          <div class="col-11">
            <div class="fw-bold">{{ vocab.word }}</div>
            <div>{{ vocab.meaning }}</div>
          </div>
          <div class="col-1 row px-0 mx-0 pt-3">
            <div class="col-6 pe-2 ps-0 text-center">{{ vocab.status }}</div>
            <input class="form-check-input col-6 px-2" type="checkbox" value="" id="flexCheckDefault">
          </div>
        </li>
      </ol>
    </div>
  </div>
</template>

<script lang="ts" setup>

import { ref, defineEmits, watch  } from 'vue';
import { Vocab } from '../../dto/vocab/Vocab';

const props = defineProps<{
  originalVocabList: Vocab[],
}>();

const emit = defineEmits(['seeVocabDetail'])

const searchResults = ref<Vocab[]>([]);
const searchWord = ref("");
const sortRule = ref("word");

watch(
  () => props.originalVocabList,
  (newVal) => {
    if (newVal) {
      searchResults.value = props.originalVocabList;
    }
  },
  { immediate: true } 
)

const sortSearchResults = () => {
  searchResults.value.sort((a, b) => {
    if (sortRule.value === 'word') {
      return a.word.localeCompare(b.word);
    } else if (sortRule.value === 'createdAt') {
      return new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime();
    } else if (sortRule.value === 'level') {
      return a.level.localeCompare(b.level );
    }
    return 0;
  });
};

const seeVocabDetail = async (searchWord: string) => {
  emit('seeVocabDetail', searchWord)
};

const updateResultList =  () => {
    searchResults.value = props.originalVocabList;
    filterVocab();
    sortSearchResults();
};

const filterVocab = async () => {
  if (searchWord.value === "") {
    searchResults.value = props.originalVocabList;
    return;
  }
  searchResults.value = props.originalVocabList.filter((vocab: Vocab) => {
    return vocab.word.includes(searchWord.value);
  });
}

defineExpose({
  updateResultList,
})

</script>