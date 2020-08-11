<template>
  <section class="section">
    <div class="buttons">
      <b-button
        type="is-success"
        expanded
        style="background-color: blueviolet"
        @click="isInsertActive = true"
        >Insertar Producto</b-button
      >
    </div>
    <b-tabs>
      <b-tab-item label="Productos en el Sistema">
        <b-table :data="productjson" :hoverable="true">
          <template slot-scope="props">
            <template v-for="column in columns">
              <b-table-column :key="column.id" v-bind="column">
                <template
                  v-if="column.searchable"
                  slot="searchable"
                  slot-scope="props"
                >
                  <b-input
                    v-model="props.filters[props.column.field]"
                    placeholder="Buscar..."
                    icon="magnify"
                    size="is-small"
                  />
                </template>
                <template v-if="column.field == 'action'">
                  <b-button
                    type="is-info"
                    @click="
                      (isEditActive = true),
                        (editProdName = props.row.nombre),
                        (editProdDescrip = props.row.descripcion),
                        (editProdPrecio = props.row.precio),
                        (editProdFoto = props.row.foto),
                        (editProdCantid = props.row.cantDisp),
                        (editID = props.row.id_producto)
                    "
                    ><b-icon icon="pencil" />
                  </b-button>
                  <b-button
                    type="is-danger"
                    @click="emitEliminarProd(props.row.id_producto)"
                  >
                    <b-icon icon="delete" />
                  </b-button>
                </template>
                {{ props.row[column.field] }}
              </b-table-column>
            </template>
          </template>
        </b-table>
      </b-tab-item>
    </b-tabs>
    <b-modal :active.sync="isInsertActive">
      <form
        action
        v-on:submit.prevent="emitInsertProd(), (isInsertActive = false)"
      >
        <div class="modal-card" style="width: auto">
          <header class="modal-card-head">
            <p class="modal-card-title">Insertar Producto</p>
          </header>
          <section class="modal-card-body">
            <b-field label="Nombre">
              <b-input type="name" v-model="insertProdName" required></b-input>
            </b-field>
            <b-field label="Descripción">
              <b-input
                type="textarea"
                minlength="10"
                maxlength="50"
                v-model="insertProdDescrip"
                required
              ></b-input>
            </b-field>
            <b-field label="Precio">
              <b-input
                type="number"
                v-model="insertProdPrecio"
                required
              ></b-input>
            </b-field>
            <b-field label="Foto">
              <b-input v-model="insertProdFoto"></b-input>
            </b-field>
            <b-field label="Cantidad Disponible">
              <b-numberinput
                min="0"
                controls-rounded
                v-model="insertProdCantid"
              ></b-numberinput>
            </b-field>
          </section>
          <footer class="modal-card-foot">
            <button class="button is-primary">
              Insertar
            </button>
          </footer>
        </div>
      </form>
    </b-modal>
    <b-modal :active.sync="isEditActive">
      <form
        action
        v-on:submit.prevent="emitEdeditProduct(editID), (isEditActive = false)"
      >
        <div class="modal-card" style="width: auto">
          <header class="modal-card-head">
            <p class="modal-card-title">Editar Producto</p>
          </header>
          <section class="modal-card-body">
            <b-field label="Nombre">
              <b-input type="name" v-model="editProdName" required></b-input>
            </b-field>
            <b-field label="Descripción">
              <b-input
                type="textarea"
                minlength="10"
                maxlength="50"
                v-model="editProdDescrip"
                required
              ></b-input>
            </b-field>
            <b-field label="Precio">
              <b-input
                type="decimal"
                v-model="editProdPrecio"
                required
              ></b-input>
            </b-field>
            <b-field label="Foto">
              <b-input v-model="editProdFoto"></b-input>
            </b-field>
            <b-field label="Cantidad Disponible">
              <b-numberinput
                v-model="editProdCantid"
                min="0"
                controls-rounded
              ></b-numberinput>
            </b-field>
          </section>
          <footer class="modal-card-foot">
            <button class="button is-primary">
              Editar
            </button>
          </footer>
        </div>
      </form>
    </b-modal>
  </section>
</template>

<script>
export default {
  props: {
    productjson: {
      type: Array
    }
  },

  data() {
    return {
      isInsertActive: false,
      insertProdName: "",
      insertProdDescrip: "",
      insertProdPrecio: "",
      insertProdFoto: "",
      insertProdCantid: 0,
      isEditActive: false,
      editProdName: "",
      editProdDescrip: "",
      editProdPrecio: "",
      editProdFoto: "",
      editProdCantid: 0,
      columns: [
        {
          field: "id_producto",
          label: "ID",
          width: "30",
          numeric: true
        },
        {
          field: "nombre",
          label: "Nombre",
          searchable: true
        },
        {
          field: "descripcion",
          label: "Descripción",
          searchable: true
        },
        {
          field: "precio",
          label: "Precio"
        },
        {
          field: "foto",
          label: "Foto"
        },
        {
          field: "cantDisp",
          label: "Cantidad"
        },
        {
          field: "action",
          label: ""
        }
      ]
    };
  },

  methods: {
    emitEliminarProd(id) {
      this.$emit("emit-delete-prod", { id });
    },

    emitInsertProd() {
      var nombre = this.insertProdName;
      var descripcion = this.insertProdDescrip;
      var precio = this.insertProdPrecio;
      var foto = this.insertProdFoto;
      var cantDisp = this.insertProdCantid;
      this.insertProdName = "";
      this.insertProdDescrip = "";
      this.insertProdPrecio = "";
      this.insertProdFoto = "";
      this.insertProdCantid = 0;
      this.$emit("emit-insert-prod", {
        nombre,
        descripcion,
        precio,
        foto,
        cantDisp
      });
    },

    emitEdeditProduct(id) {
      var nombre = this.editProdName;
      var descripcion = this.editProdDescrip;
      var precio = this.editProdPrecio;
      var foto = this.editProdFoto;
      var cantDisp = this.editProdCantid;
      this.editProdName = "";
      this.editProdDescrip = "";
      this.editProdPrecio = "";
      this.editProdFoto = "";
      this.editProdCantid = 0;
      this.$emit("emit-edit-prod", {
        id,
        nombre,
        descripcion,
        precio,
        foto,
        cantDisp
      });
    }
  }
};
</script>
