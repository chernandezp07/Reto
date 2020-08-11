<template>
  <div
    class="column is-3 is-full-mobile is-half-tablet is-one-third-desktop is-one-quarter- widescreen"
  >
    <div class="card">
      <header class="card-header styleHeaderCard">
        <p class="card-header-title has-text-grey">
          {{ tiend.nombre }}
        </p>
      </header>
      <div class="card-content">
        <div class="content has-text-centered">
          <b-icon :icon="icon" size="is-large" type="is-primary" />
        </div>
      </div>
      <footer class="card-footer">
        <div class="card-footer-item">
          <span>
            <slot />
          </span>
        </div>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    icon: {
      type: String,
      required: true
    },
    tiend: {
      type: Object
    }
  }
};
</script>

<style>
.styleHeaderCard {
  background: linear-gradient(to left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.55));
  background-color: rgb(0, 0, 0);
  background: linear-gradient(to right, rgb(255, 255, 255), rgb(126, 126, 126));
}
</style>
