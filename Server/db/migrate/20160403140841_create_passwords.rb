class CreatePasswords < ActiveRecord::Migration
  def change
    create_table :passwords do |t|
      t.string :key
      t.string :msg

      t.timestamps null: false
    end
  end
end
