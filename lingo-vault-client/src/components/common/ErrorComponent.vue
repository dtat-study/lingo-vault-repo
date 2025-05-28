<template>
    <div class="error-container">
            <div class="icon"><CloudAlert :size="70"/></div>
            <div class="message"> An unexpected error occurred. Please try again later or contact support :'<</div>
    </div>
</template>
<script lang="ts" setup>
import { CloudAlert } from 'lucide-vue-next';    
</script>