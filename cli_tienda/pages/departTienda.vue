<template>
  <section class="sectionfrontt">
    <div v-if="departList[0] != null">
      <b-tabs position="is-centered" type="is-boxed">
        <b-tab-item
          v-for="depart in departList"
          :key="depart.id"
          :label="depart.nombre"
        >
          <div v-if="depart.product[0] != null">
            <div class="columns is-mobile is-multiline">
              <cardProduct
                v-for="prodct in depart.product"
                :key="prodct.id"
                :prodct="prodct"
                icon="github"
                @emit-add-car="addCarProd"
              ></cardProduct>
            </div>
          </div>
          <div v-else>No hay Productos que mostrar</div>
        </b-tab-item>
      </b-tabs>
    </div>
    <div v-else>No hay Departamentos en esta tienda</div>
  </section>
</template>

<script>
import CardProduct from "~/components/CardProduct";

export default {
  components: {
    CardProduct
  },

  data() {
    return {
      id_tienda: "",
      departList: "",
      temp: [],
      idlist: [],
      init: []
    };
  },

  mounted() {
    if (JSON.parse(localStorage.getItem("prodct")) == null) {
      const parsed = JSON.stringify([]);
      localStorage.setItem("prodct", parsed);
    }
    if (!localStorage.id_tienda) {
      localStorage.id_tienda = this.$route.params.id_tienda;
    } else if (this.$route.params.id_tienda) {
      localStorage.id_tienda = this.$route.params.id_tienda;
    }
    this.id_tienda = localStorage.id_tienda;
    this.$axios.get("/api/tienda/" + this.id_tienda).then(res => {
      this.departList = res.data.depart;
    });
    console.log(this.id_tienda);
  },

  methods: {
    clickB: function() {
      console.log(this.$store.state.product);
    },
    addCarProd: function(params) {
      this.temp = JSON.parse(localStorage.getItem("prodct"));
      this.temp.forEach(element => {
        this.idlist.push(element.id_producto);
      });
      if (this.idlist.indexOf(params.prod.id_producto) == -1) {
        this.temp.push(params.prod);
        const parsed = JSON.stringify(this.temp);
        localStorage.setItem("prodct", parsed);
      }
    }
  },

  watch: {
    id_tienda(newId_tienda) {
      localStorage.id_tienda = newId_tienda;
    }
  }
};
</script>

<style>
.sectionfrontt {
  padding-top: 0px !important;
  position: relative;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-attachment: fixed;
  font-family: Roboto, sans-serif;
}
</style>
