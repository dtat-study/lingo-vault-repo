<template>
  <div class="vocab-show">
    <div class="vocab-show-option">
      <div class="text-center">Choose the word you want to show!</div>
      <div class="row">
        <div class="col-6">
          <select class="form-select" @change="sortSearchResults()" v-model="sortRule">
            <option value="word">Alphabet</option>
            <option value="created_at">Added Order</option>
            <option value="priority">Learning Progress</option>
          </select>
        </div>
        <div class="col-6">
          <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Search..." v-model="searchWord" @keyup="filterVocab()">
            <button class="btn btn-outline-secondary" type="button" @click="filterVocab()">Search</button>
          </div>
        </div>
      </div>

    </div>
    <div class="vocab-show-result">
      <ol>
        <li v-for="vocab in searchResults" class="list-group-item d-flex align-items-start row" :class="vocab.priority"
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
import { Vocab } from '../dto/vocab/Vocab';
import { ref, onMounted, defineEmits, watch  } from 'vue';

const props = defineProps<{
  originalWordList: Vocab[],
}>();

const searchResults = ref<Vocab[]>([]);
const searchWord = ref("");
const sortRule = ref("word");
watch(
  () => props.originalWordList,
  (newVal) => {
    if (newVal) {
      searchResults.value = props.originalWordList;
    }
  },
  { immediate: true } 
)

const emit = defineEmits(['seeVocabDetail'])

const sortSearchResults = () => {
  const priorityOrder: Record<string, number> = { ng: 0, normal: 1, good: 2 };

  searchResults.value.sort((a, b) => {
    if (sortRule.value === 'word') {
      return a.word.localeCompare(b.word);
    } else if (sortRule.value === 'created_at') {
      return new Date(a.created_at).getTime() - new Date(b.created_at).getTime();
    } else if (sortRule.value === 'priority') {
      return (priorityOrder[a.priority] || 0) - (priorityOrder[b.priority] || 0);
    }
    return 0;
  });
};

const seeVocabDetail = async (searchWord: string) => {
  emit('seeVocabDetail', searchWord)
};

const updateResultList =  () => {
    searchResults.value = props.originalWordList;
    filterVocab();
    sortSearchResults();
};

const filterVocab = async () => {
  if (searchWord.value === "") {
    searchResults.value = props.originalWordList;
    return;
  }
  searchResults.value = props.originalWordList.filter((vocab: Vocab) => {
    return vocab.word.includes(searchWord.value);
  });
}

defineExpose({
  updateResultList,
})

</script>