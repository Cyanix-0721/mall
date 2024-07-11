<script lang="ts" setup>
import { getConfig } from "@/config";

const TITLE = getConfig("Title");
</script>

<style lang="scss" scoped>
.layout-footer {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 0 0 8px;
  font-size: 14px;
}
</style>
