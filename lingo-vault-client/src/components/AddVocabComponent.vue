<template>
   <div class="col-2" >
        <label for="word" class="form-label">Word</label>
        <input type="text" class="form-control" v-model="newVocab.word" id="word" @keyup="checkExistVocab()" :class="{ 'duplicated' : isExistVocabAndMeaning}">
        <div :class="{'duplicated-message': isExistVocab && !isExistVocabAndMeaning}" style="display: none;">
            <button @click="seeVocabDetail"  class="btn btn-outline-warning border border-0 px-0">Exist this word - See detail</button>
        </div>
    </div>
    <div class="col-4">
        <label for="meaning" class="form-label">Meaning</label>
        <input  type="text" class="form-control" v-model="newVocab.meaning" id="meaning" @keyup="checkExistVocab()" :class="{ 'duplicated' : isExistVocabAndMeaning}">
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
            <input class="form-check-input" type="checkbox" name="classification" :id="classification"
            v-model="newVocabClassifications" :value="classification">
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
import *  as restApi from "../connect/restApi";

const props = defineProps<{
  originalVocabList: Vocab[],
}>();

const emit = defineEmits(['seeVocabDetail'])

const classifications = ref(["n","v","adj"  ,"adv","prep","conj","pron","det","interj"]); 
const newVocabClassifications = ref([]); 
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
    if(isExistVocabAndMeaning.value === true) {
        alert("This word and meaning already exist. Please check again.")
        return;
    }

    newVocab.value.classification = "";
    newVocabClassifications.value.forEach((classification: string) => {
        newVocab.value.classification += classification + " ";
    });
    newVocab.value.classification = newVocab.value.classification.trim();

    let result = await restApi.addNewVocabApi(newVocab.value);

    if(result === true) {
        alert("Add new vocab successfully.")
    } else {
        alert("Failed to add new vocab. Please check again.")
    }
}

const seeVocabDetail = async () => {
    emit('seeVocabDetail', newVocab.value.word) 
}
</script>