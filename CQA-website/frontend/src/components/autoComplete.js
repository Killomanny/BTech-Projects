function matching(str,arr,type){
    var j = str.length,i;
    var matches = [];
    var result = [];
    for ( i = 0; i<arr.length;i++)
    {
        if(type === "tags")
        {
            if(arr[i].tag_name.substr(0,j) === str) {
                matches.push({tag_name : arr[i].tag_name , id : arr[i].id});
            }    
        }
        if(type === "users")
        {
            if(arr[i].user_name.substr(0,j) === str) {
                matches.push({user_name : arr[i].user_name , id : arr[i].id});
            }    
        }
    }
    for( i=0; i<30 && i < matches.length ;i++) result.push(matches[i]);
    return result;
    // return matches;
}

export default matching;