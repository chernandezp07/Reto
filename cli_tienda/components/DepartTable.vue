<template>
  <section class="section">
    <div class="buttons">
      <b-button
        type="is-success"
        expanded
        style="background-color: blueviolet"
        @click="(isInsertActive = true), getFilteredTags"
        >Insertar Departamento</b-button
      >
    </div>
    <b-tabs>
      <b-tab-item label="Departamentos del Sistema">
        <b-table :data="departjson" :hoverable="true">
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
                        (editDepartN = props.row.nombre),
                        (editDepartTags = props.row.product),
                        (editID = props.row.id_departamento),
                        getFilteredTags
                    "
                    ><b-icon icon="pencil" />
                  </b-button>
                  <b-button
                    type="is-danger"
                    @click="emitEliminarDepart(props.row.id_departamento)"
                  >
                    <b-icon icon="delete" />
                  </b-button>
                </template>
                <template v-if="column.field == 'products'">
                  <p v-for="product in props.row.product" :key="product.id">
                    {{ product.nombre }}
                  </p>
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
        v-on:submit.prevent="emitInsertDepart(), (isInsertActive = false)"
      >
        <div class="modal-card" style="width: auto">
          <header class="modal-card-head">
            <p class="modal-card-title">Insertar Departamento</p>
          </header>
          <section class="modal-card-body">
            <b-field label="Nombre">
              <b-input
                type="name"
                v-model="insertDepartName"
                required
              ></b-input>
            </b-field>
            <b-field label="Enter some tags">
              <b-taginput
                v-model="tags"
                :data="filteredTags"
                autocomplete
                :allow-new="allowNew"
                :open-on-focus="openOnFocus"
                field="nombre"
                icon="label"
                placeholder="Add a tag"
              >
              </b-taginput>
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
        v-on:submit.prevent="emitEditDepart(editID), (isEditActive = false)"
      >
        <div class="modal-card" style="width: auto">
          <header class="modal-card-head">
            <p class="modal-card-title">Editar Departamento</p>
          </header>
          <section class="modal-card-body">
            <b-field label="Nombre">
              <b-input type="name" v-model="editDepartN" required></b-input>
            </b-field>
            <b-field label="Enter some tags">
              <b-taginput
                v-model="editDepartTags"
                :data="filteredTags"
                autocomplete
                :allow-new="allowNew"
                :open-on-focus="openOnFocus"
                field="nombre"
                icon="label"
                placeholder="Add a tag"
              >
              </b-taginput>
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
    departjson: {
      type: Array
    },
    productjson: {
      type: Array
    }
  },

  computed: {
    getFilteredTags(text) {
      this.filteredTags = this.productjson.filter(option => {
        return option.nombre.toString();
      });
    }
  },

  data() {
    return {
      isInsertActive: false,
      insertDepartName: "",
      filteredTags: this.productjson,
      tags: [],
      openOnFocus: true,
      isSelectOnly: false,
      allowNew: false,
      isEditActive: false,
      editID: "",
      editDepartN: "",
      editDepartTags: [],
      columns: [
        {
          field: "id_departamento",
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
          field: "products",
          label: "Productos"
        },
        {
          field: "action",
          label: ""
        }
      ]
    };
  },

  methods: {
    emitEliminarDepart(id) {
      this.$emit("emit-delete-depart", { id });
    },

    emitInsertDepart() {
      var nombre = this.insertDepartName;
      var listProd = this.tags;
      this.insertDepartName = "";
      this.tags = "";
      this.$emit("emit-insert-depart", { nombre, listProd });
    },

    emitEditDepart(id) {
      var nombre = this.editDepartN;
      var listProd = this.editDepartTags;
      this.editDepartN = "";
      this.editDepartTags = [];
      this.$emit("emit-edit-depart", { id, nombre, listProd });
    }
  }
};
</script>
