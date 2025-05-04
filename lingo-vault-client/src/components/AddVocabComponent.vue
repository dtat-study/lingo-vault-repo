<template>
   <div class="col-2" >
        <label for="word" class="form-label">Word</label>
        <input type="text" class="form-control" v-model="newVocab.word" id="word" @change="checkExistVocab()" :class="{ 'duplicated' : isExistVocabAndMeaning}">
        <div :class="{'duplicated-message': isExistVocab && !isExistVocabAndMeaning}" style="display: none;">
            <button @click="seeVocabDetail"  class="btn btn-outline-warning border border-0 px-0">Exist this word - See detail</button>
        </div>
    </div>
    <div class="col-4">
        <label for="meaning" class="form-label">Meaning</label>
        <input  type="text" class="form-control" v-model="newVocab.meaning" id="meaning" @change="checkExistVocab()" :class="{ 'duplicated' : isExistVocabAndMeaning}">
        <div :class="{'duplicated-error-message': isExistVocabAndMeaning}" style="display: none;">
            <button @click="seeVocabDetail" class="btn btn-outline-danger border border-0 px-0">Exist exactly the same word - See detail</button>
        </div>
    </div>
    <div class="col-6">
        <label for="source" class="form-label">Notice</label>
        <input type="text" class="form-control" v-model="newVocab.notice" id="notice">
    </div>
    <div class="col-6">
        <label for="use" class="form-label">Example</label>
        <input type="text" class="form-control" v-model="newVocab.example" id="example">
    </div>
    <div class="col-5">
        <div for="use" class="form-label">Type</div>
        <div class="form-check form-check-inline" v-for="classification in classifications">
            <input class="form-check-input" type="radio" name="classification" :id="classification"
            v-model="newVocab.classification" :value="classification">
            <label class="form-check-label" :for=classification>{{ classification }} </label>
        </div>
    </div>
    <div class="col-1 add-button-div">
        <button type="button" class="btn btn-primary mt-3 add-button" @click="addNewVocab()">Add</button>
    </div>

</template>

<script lang="ts" setup>

import { Vocab } from '../dto/vocab/Vocab';
import { ref } from 'vue';
import axiosClient from "../config/axiosConfig";

const props = defineProps<{
  originalVocabList: Vocab[],
}>();

const emit = defineEmits(['seeVocabDetail'])

const classifications = ref(["n","v","adj"  ,"adv","prep","conj","pron","det","interj"]); 
const newVocab = ref(new Vocab());
const isExistVocab = ref(false);
const isExistVocabAndMeaning = ref(false);

const checkExistVocab = () => {
    isExistVocabAndMeaning.value =
     props.originalVocabList.some((vocab : Vocab) => {
        return vocab.word === newVocab.value.word && vocab.meaning === newVocab.value.meaning;
    });

    isExistVocab.value =
     props.originalVocabList.some((vocab : Vocab) => {
        return vocab.word === newVocab.value.word ;
    });
}

const addNewVocab = async () => {
    await axiosClient
        .post('/addNewVocab' ,newVocab.value)
        .then() // neead action check if true or false
}

const seeVocabDetail = async () => {
    emit('seeVocabDetail', newVocab.value.word) 
}
</script>