class CategoriesController < ApplicationController
  before_action :confirm_user, only: [:new, :edit, :create, :update, :destroy]
  before_action :confirm_admin, only: [:update, :edit, :destroy]
  publishes_to :category
  
  # GET /categories
  # GET /categories.json
  def index
 
    id = SecureRandom.uuid.to_s

    category_info = {:id => id, :type => "index" }

    publish :category, JSON.generate(category_info)  

    @categories = get_categories(id)  
  end

  def new_categories_for_auction
    @auction_id = params[:id]

    id = SecureRandom.uuid.to_s    

    category_info = {:id => id, :type => "index" }

    publish :category, JSON.generate(category_info)  

    @categories = get_categories(id)      


  end

  def create_categories_for_auction
    auction_id = params[:id]
    categories = params[:category_ids]

    id = SecureRandom.uuid.to_s    

    category_info = {:id => id, :type => "add_to_auction",
      :auction_id => auction_id,
      :categories => categories
    }

    publish :category, JSON.generate(category_info)  

    @categories = get_categories(id)  

  end

  # GET /categories/1
  # GET /categories/1.json
  def show
  end

  # GET /categories/new
  def new
    @category = Category.new
  end

  # GET /categories/1/edit
  def edit
  end

  # POST /categories
  # POST /categories.json
  def create
    category = Category.new(category_params)

    id = SecureRandom.uuid.to_s

    category_info = {:id => id, :type => "create", 
      :category_name => category.category     
    }

    publish :category, JSON.generate(category_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "New category created!"
    else
      @status = "Category could not be created"
    end

    render 'confirm'    
    # @category = Category.new(category_params)

    # respond_to do |format|
    #   if @category.save
    #     format.html { redirect_to @category, notice: 'Category was successfully created.' }
    #     format.json { render :show, status: :created, location: @category }
    #   else
    #     format.html { render :new }
    #     format.json { render json: @category.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # PATCH/PUT /categories/1
  # PATCH/PUT /categories/1.json
  def update
    category = Category.new(category_params)

    id = SecureRandom.uuid.to_s

    category_info = {:id => id, :type => "update", 
      :category_id => params[:id],
      :category_name => category.category     
    }

    publish :category, JSON.generate(category_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Category updated!"
    else
      @status = "Category could not be updated"
    end

    render 'confirm'  
        # respond_to do |format|
    #   if @category.update(category_params)
    #     format.html { redirect_to @category, notice: 'Category was successfully updated.' }
    #     format.json { render :show, status: :ok, location: @category }
    #   else
    #     format.html { render :edit }
    #     format.json { render json: @category.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # DELETE /categories/1
  # DELETE /categories/1.json
  def destroy
    id = SecureRandom.uuid.to_s

    category_info = {:id => id, :type => "delete", 
      :category_id => params[:id]
    }

    publish :category, JSON.generate(category_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Category deleted!"
    else
      @status = "Category could not be deleted"
    end

    render 'confirm' 
        # @category.destroy
    # respond_to do |format|
    #   format.html { redirect_to categories_url, notice: 'Category was successfully destroyed.' }
    #   format.json { head :no_content }
    # end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_category
      redirect_to "/users/new", notice: "You must log in or sign up to create a new auction"
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def category_params
      params.require(:category).permit(:category)
    end
end
